package com.cesararaujostudio.api.service;

import com.cesararaujostudio.api.bases.PageReq;
import com.cesararaujostudio.api.bases.PageRes;
import com.cesararaujostudio.api.config.i18n.Messages;
import com.cesararaujostudio.api.config.i18n.ServiceException;
import com.cesararaujostudio.api.config.security.AuthUtil;
import com.cesararaujostudio.api.dto.req.UserReqDTO;
import com.cesararaujostudio.api.dto.res.UserResDTO;
import com.cesararaujostudio.api.model.User;
import com.cesararaujostudio.api.repository.UserRepository;
import com.cesararaujostudio.api.util.SearchUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenStore tokenStore;

    public PageRes<UserResDTO> findAll(PageReq query) {

        Specification<User> deleted = SearchUtils.specByDeleted(query.isDeleted());
        Specification<User> filters = SearchUtils.specByFilter(query.getFilter(), "username", "id", "email", "name",
                "city", "phone");

        var page = this.repository.findAll(deleted.and(filters), query.toPageRequest());

        return new PageRes<UserResDTO>(page.getContent().stream().map(UserResDTO::of).collect(Collectors.toList()),
                page.getTotalElements(), page.getTotalPages());
    }

    public UserResDTO findById(Long id) {
        return UserResDTO.of(this.repository.findById(id).orElseThrow(() -> new ServiceException(Messages.user_not_found)));
    }

    public UserResDTO update(Long id, UserReqDTO dto) {

        var user = dto.toEntity(this.repository.findById(id).orElseThrow(() -> new ServiceException(Messages.user_not_found)));

        return UserResDTO.of(this.save(user));
    }

    public UserResDTO create(UserReqDTO dto) {
        return UserResDTO.of(this.save(dto.toEntity(new User())));
    }

    public User save(User pojo) {

        if (LocalDate.now().isBefore(pojo.getBirthdate()))
            throw new ServiceException(Messages.user_not_found);

        if (StringUtils.hasText(pojo.getNewPassword()))
            pojo.setEncryptedPassword(this.encode(pojo.getNewPassword()));

        if (pojo.getId() == null)
            pojo.setEncryptedPassword(this.encode(pojo.getEncryptedPassword()));

        return this.repository.findById(this.repository.save(pojo).getId()).orElseThrow(() -> new ServiceException(Messages.user_not_found));
    }

    public void logicalExclusion(Long id) {

        this.repository.findByIdAndNotDeleted(id).ifPresentOrElse(
                user -> this.repository.softDelete(user.getId()),
                () -> {
                    throw new ServiceException(Messages.user_not_found);
                });
    }

    public void restoreDeleted(Long id) {

        this.repository.findDeletedById(id).ifPresentOrElse(
                user -> this.repository.restoreDeleted(user.getId()),
                () -> {
                    throw new ServiceException(Messages.user_not_found);
                });
    }

    public User findAuthenticatedUser() {
        return this.repository.findById(AuthUtil.getUserId())
                .orElseThrow(() -> new ServiceException("Usuário deve estar autenticado."));
    }

    public void logout() {
        if (SecurityContextHolder.getContext().getAuthentication() instanceof OAuth2Authentication) {
            OAuth2AccessToken accessToken = tokenStore
                    .getAccessToken((OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication());
            if (accessToken != null && accessToken.getRefreshToken() != null) {
                this.tokenStore.removeAccessTokenUsingRefreshToken(accessToken.getRefreshToken());
                this.tokenStore.removeRefreshToken(accessToken.getRefreshToken());
            }
        }
    }

    private String encode(String rawPassword) {
        if (StringUtils.containsWhitespace(rawPassword))
            throw new ServiceException("A senha não pode conter espaços em branco.");
        return this.passwordEncoder.encode(rawPassword);
    }

}
