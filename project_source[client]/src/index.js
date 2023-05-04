import React from 'react';
import ReactDOM from 'react-dom/client';
import $ from 'jquery';
import reportWebVitals from './reportWebVitals';

import 'bootstrap/dist/css/bootstrap.css';
import "react-datetime/css/react-datetime.css";
import './index.css';

import Main from './MainContainer/MainContainer';
import Nav from './Nav/Nav';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <Nav />
    <Main />
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();

$(function () {
  //$("body").css("opacity", 0);
});
