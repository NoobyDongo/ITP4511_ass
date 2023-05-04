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
        this.switchPage = this.switchPage.bind(this);
    }
    switchPage() {
        f.showPage(this.action);
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
        return(
            <li key={key++} className="nav-item">
                {super.render()}
            </li>
        );
    }
}
class UserCorner extends React.Component{
    constructor(props) {
        super(props);
        this.list = props.list || []
        this.username = props.username || "Guest";
    }
    render() {
        return(
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

export default function Nav() {

    var userid = Cookies.get('userid');
    var username = Cookies.get('username');

    console.log({username:username, userid:userid})

    var dropDownList = [
        {name:"Register Account", action:"register"}, 
    ]
    dropDownList.push({name:"Login", action:"login"})

    if(false){
        dropDownList = dropDownList.concat([
            {name:"Manage Account", action:"account"},
        {name:"Manage Bookings", action:"booking"},
        {name:"Manage Guest", action:"guest"},
        {name:"Logout", action:"logout"}
        ])
    }

    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light border-bottom">
            <a className="navbar-brand vcentered" href="#"><img src="http://localhost:9000/logo" /></a>
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText"
                aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarText">
                <ul className="navbar-nav w-100 vcentered">
                    <NavOption name="Home" action="front"/>
                    <NavOption name="Venue" action="venue"/>
                    <UserCorner list={dropDownList}/>
                </ul>
            </div>
        </nav>
    );
}