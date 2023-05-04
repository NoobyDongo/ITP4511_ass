import React from "react";
import Cookies from 'js-cookie';
import './Nav.css';
import * as f from '../_functions.js';

var key = 0;
class NavLink extends React.Component {
    constructor(props) {
        super(props);
        this.key = props.key;
        this.name = props.name;
        this.action = props.action || "front";
        if (props.switchPage)
            this.switchPage = props.switchPage.bind(this);
    }

    render() {
        return (
            <a key={key++} className="nav-link" onClick={this.switchPage} href="#">{this.name}</a>
        );
    }
}
class NavOption extends NavLink {
    constructor(props) {
        super(props);
    }
    render() {
        return (
            <li key={key++} className="nav-item">
                {super.render()}
            </li>
        );
    }
}
class UserCorner extends React.Component {
    constructor(props) {
        super(props);
        this.list = props.list || []
        this.username = props.username || "Guest";
    }
    render() {
        return (
            <li className="nav-item dropdown ml-auto">
                <a className="nav-link dropdown-toggle vcentered" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="false">
                    <div>{this.username}</div>
                    <span className="fill material-symbols-rounded">account_circle</span>
                </a>
                <div className="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    {this.list.map((object) => {
                        return new NavLink(object).render()
                    })}
                </div>
            </li>
        );
    }
}

export default function Nav({toEmail, logout, user, toHome, toBooking, toVenue, toGuest, toRegister, toLogin }) {

    var dropDownList = [
        { name: "Register Account", action: "register", switchPage: toRegister },
    ]
    if (!user || !user.id)
        dropDownList.push({ name: "Login", action: "login", switchPage: toLogin })

    if (user && user.id) {
        dropDownList = dropDownList.concat([
            { name: "Logout", action: "logout", switchPage: logout }
        ])
    }
    dropDownList.push({ name: "Send Email", action: "email", switchPage: toEmail })

    return (
        <nav className="navbar navbar-expand-lg">
            <a onClick={toHome} className="navbar-brand vcentered" href="#"><img src="http://localhost:9000/logo" /></a>
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText"
                aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarText">
                <ul className="navbar-nav w-100 vcentered">
                    <NavOption switchPage={toHome} name="Home" action="front" />
                    <NavOption switchPage={toVenue} name="Venue" action="venue" />
                    {user && user.id && <NavOption switchPage={toBooking} name="Booking" action="booking" />}
                    {user && user.id && <NavOption switchPage={toGuest} name="Guest" action="guest" />}
                    <UserCorner username={(user && user.name) || "Guest"} list={dropDownList} />
                </ul>
            </div>
        </nav>
    );
}