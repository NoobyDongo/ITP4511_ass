import React, { useEffect, useState } from "react";
import logo from '../logo.svg';
import $ from 'jquery';
import './MainContainer.css';

function Front() {
    return (
      <div id="front">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <p>
            Edit <code>src/App.js</code> and save to reload.
          </p>
          <a
            className="App-link"
            href="https://reactjs.org"
            target="_blank"
            rel="noopener noreferrer"
          >
            Learn React
          </a>
        </header>
      </div>
    );
  }

export default function MainContainer() {

    return (
      <div class="main-container">
        <Front/>
        <input class="form-control" type="text" id="time"/>
                <input class="form-control" type="text" id="datetime1"/>


                <div class="form-group">
                    <div class='input-group date' id='datetime'>
                        <input type='text' class="form-control" />
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>
                </div>
      "dwadwdwa"dwdwadwadawwwwwwwwwwwwww
      </div>
    )
}