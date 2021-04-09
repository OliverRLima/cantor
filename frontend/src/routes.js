import React from "react";
import { HashRouter, Route, Redirect } from "react-router-dom";
import { isAuthenticated } from "./auth";
import Switch from "react-bootstrap/esm/Switch";

import Home from "./pages/home";
import Login from "./pages/login";
import Feed from "./pages/feed";
import Medalhas from "./pages/medalhas";
import Perfil from "./pages/perfil";
import Cadastro from "./pages/cadastro";
import Convite from "./pages/convite.js";
import infoComp from "./pages/infoComp";
import alterarInfo from "./pages/alterarInfo";
import publicacao from "./pages/publicacao";


const PrivateRoute = ({ component: Component, ...rest }) => (
  <Route
    {...rest}
    render={(props) =>
      isAuthenticated() ? (
        <Component {...props} />
      ) : (
          <Redirect
            to={{ pathname: "/login", state: { from: props.location } }}
          />
        )
    }
  />
);

function Routes() {
  return (
    <HashRouter>
      <Switch>
        <Route exact path="/" component={Home} />
        <Route exact path="/login" component={Login} />
        <Route exact path="/cadastro" component={Cadastro} />
        <PrivateRoute exact path="/feed/:id" component={Feed} />
        <PrivateRoute exact path="/medalhas/:id" component={Medalhas} />
        <PrivateRoute exact path="/perfil/:id" component={Perfil} />
        <PrivateRoute exact path="/convite/:id" component={Convite} />
        <PrivateRoute exact path="/informacoes/:id" component={infoComp} />
        <PrivateRoute exact path="/informacoes/alterar/:id" component={alterarInfo} />
        <PrivateRoute exact path="/publicacao/:id" component={publicacao} />
      </Switch>
    </HashRouter>
  );
}

export default Routes;
