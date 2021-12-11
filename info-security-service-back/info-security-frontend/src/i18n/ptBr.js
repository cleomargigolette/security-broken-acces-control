import ptbrMessages from 'ra-language-portuguese';

ptbrMessages.messages = {
  required: 'Preenchimento obrigatório',
  loading: 'Carregando',
  wait: 'Aguarde',
};

ptbrMessages.page = {
  empty: 'Nenhum registro encontrado',
  invite: 'Deseja criar um novo?',
};

ptbrMessages.enums = {
  roles: {
    ADMIN: 'Admin',
    CUSTOMER: 'Cliente'
  }
};

ptbrMessages.forms = {
  summary: 'Cadastro',
};

ptbrMessages.mbrnTable = {
  pagination: {
    labelDisplayedRows: '{from} - {to} de {count}',
    labelRowsSelect: 'registros',
    labelRowsPerPage: 'Registros por página:',
    firstAriaLabel: 'Primeira página',
    firstTooltip: 'Primeira página',
    previousAriaLabel: 'Página anterior',
    previousTooltip: 'Página anterior',
    nextAriaLabel: 'Próxima página',
    nextTooltip: 'Próxima página',
    lastAriaLabel: 'Última página',
    lastTooltip: 'Última página',
  },
  toolbar: {
    nRowsSelected: '{0} linha(s) selecionada',
  },
  header: {
    actions: 'Ações',
  },
  body: {
    emptyDataSourceMessage: 'Nenhum registro encontrado',
    filterRow: {
      filterTooltip: 'Filtrar',
    },
  },
};

ptbrMessages.resources = {
  users: {
    empty: ptbrMessages.page.empty,
    invite: ptbrMessages.page.invite,
    name: 'Usuário |||| Usuários',
    fields: {
      updatedAt: 'Atualizado em',
      updatedBy: 'Atualizado por',
      username: 'Login',
      name: 'Nome Completo',
      password: 'Nova Senha',
      email: 'E-mail',
      roles: 'Permissões',
      phone: 'Telefone',
      city: 'Cidade',
      birthdate: 'Data de Nascimento',
      encryptedPassword: 'Senha',
      comments: 'Observações',
    },
  }
};

export default ptbrMessages;
