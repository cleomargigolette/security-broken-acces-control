import React from 'react';
import {
  translate,
  CheckboxGroupInput,
  RadioButtonGroupInput,
} from 'react-admin';
import Chip from '@material-ui/core/Chip';
import styled from 'styled-components';
import { Typography } from '@material-ui/core';
const enums = {
  roles: ['ADMIN', 'CUSTOMER'],

};
const enumsStyles = {
  roles: [
    { backgroundColor: 'rgb(24, 67, 54)', color: '#fff' },
    { backgroundColor: 'rgb(76, 175, 80)', color: '#fff' },
  ]
};

const choices = source => {
  const styleArr = enumsStyles[source];
  return enums[source].map((choice, idx) => {
    return {
      id: choice,
      name: `enums.${source}.${choice}`,
      style: styleArr && styleArr[idx] ? styleArr[idx] : undefined,
    };
  });
};

const ChipLabel = translate(({ translate, item, source }) => {
  const found = choices(source).find(choice => choice.id === item);
  return (
    <Chip label={found ? translate(found.name) : ''} style={found.style} />
  );
});

const SimpleEnumField = translate(
  ({ translate, source, record = {}, type = source }) => {
    const status = translate(`enums.${type}.${record[source]}`);
    return <Typography>{status}</Typography>;
  }
);

const EnumField = ({ source, record = {}, type }) => (
  <>
    {record[source] &&
      record[source].map(item => (
        <ChipLabel key={item} item={item} source={!type ? source : type} />
      ))}
  </>
);

const EnumCheckboxInput = ({ resource, source, validate, type }) => (
  <CheckboxGroupInput
    label={`resources.${resource}.fields.${source}`}
    source={source}
    choices={choices(!type ? source : type)}
    validate={validate}
  />
);

const StyledRadioButtonGroupInput = styled(RadioButtonGroupInput)`
  margin: 0 !important;
  div {
    flex-direction: row;
  }
`;

const EnumRadioField = ({ source, record = {}, type }) => (
  <>
    {record[source] && (
      <ChipLabel
        key={record[source]}
        item={record[source]}
        source={!type ? source : type}
      />
    )}
  </>
);

const EnumRadioInput = ({ resource, source, validate, type }) => (
  <StyledRadioButtonGroupInput
    label={`resources.${resource}.fields.${source}`}
    source={source}
    choices={choices(!type ? source : type)}
    validate={validate}
  />
);
export {
  EnumField,
  EnumCheckboxInput,
  EnumRadioField,
  EnumRadioInput,
  SimpleEnumField,
};
