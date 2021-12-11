import React from 'react';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import { Title } from 'react-admin';

export default () => (
  <Card>
    <Title title="Falta de Permissão" />
    <CardContent>
      <h1>Ops! Você não tem privilégios de admin.</h1>
    </CardContent>
  </Card>
);
