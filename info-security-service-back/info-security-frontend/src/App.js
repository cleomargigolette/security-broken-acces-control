import React from 'react';
import { Admin, Resource } from 'react-admin';
import Roles from './constants/Roles';
import i18nProvider from './i18n';
import LoginPage from './pages/LoginPage';
import ErrorPermition from './pages/ErrorPermition';
import Users from './pages/Users'; 
import authProvider from './providers/authProvider';
import dataProvider from './providers/dataProvider';
import './styles/App.css';
import theme from './themes/theme';

const fetchResources = permissions => {
  let arr = [];
  // permissions.includes(Roles.ADMIN) ? arr.push(<Resource name="users" {...Users} />) : arr.push(<Resource name="catchAll" {...ErrorPermition} />)
  arr.push(<Resource name="users" {...Users} />)
  return arr;
};

function App() {
  return (
    <div className="App">
      <Admin
        theme={theme}
        i18nProvider={i18nProvider}
        authProvider={authProvider}
        dataProvider={dataProvider}
        loginPage={LoginPage}
        catchAll={ErrorPermition}
      >
        {fetchResources}
      </Admin>
    </div>
  );
}

export default App;
