import React, { useEffect, useState } from 'react';
import Datetime from 'react-datetime';
import moment from 'moment';
import * as f from './functions';
import * as i from './Input';
import Cookies from 'js-cookie';

var key = 0;
const userid = Cookies.get('userid');
const username = Cookies.get('username');
export default function Booking({venueid}) {

    const [venue, setVenue] = useState()
    const [guests, setGuest] = useState();
    useEffect(() => {
      f.GetAllFrom("http://localhost:8088/guest/ALL/" + userid, setGuest)
      f.GetAllFrom("http://localhost:8088/venue/" + venueid, setVenue)
    }, []);
  
    class Guest extends React.Component {
      constructor(props) {
        super(props);
        this.editGuest = this.editGuest.bind(this)
      }
  
      editGuest() {
  
      }
  
      render() {
        if (!this.props.simple)
          return (
            <tr key={this.props.key}>
              <th scope="row">{this.props.key}</th>
              <td>{this.props.name}</td>
              <td>{this.props.email}</td>
              <td>
                <i.Button text="Edit" action={this.editGuest} />
              </td>
            </tr>)
        else
          return (
            <div key={key++} className="list-group-item list-group-item-action">
              {this.props.name}
              <br />
              {this.props.email}
            </div>
          )
      }
    }
    class GuestList extends React.Component {
      constructor(props) {
        super(props);
        this.count = 1;
      }
  
      render() {
        return (
          <div id={this.props.id || "guest"} className={this.props.simple && "bookingGuest"}>
            {!this.props.simple && (<h3>Guest List</h3>)}
  
            {!this.props.simple ?
              (
                <table className="table">
                  <thead>
                    <tr>
                      <th scope="col"></th>
                      <th scope="col">Name</th>
                      <th scope="col">Email</th>
                      <th scope="col"></th>
                    </tr>
                  </thead>
                  <tbody>
                    {f.CheckList(guests, "There are no avalible guest.") || guests.map((object) => {
                      object.key = this.count++;
                      return new Guest(object).render()
                    })}
                  </tbody>
                </table>
              ) : (
                <div className="list-group">
                  {f.CheckList(guests, "There are no avalible guest.") || guests.map((object) => {
                    object.simple = true;
                    return new Guest(object).render()
                  })}
                </div>
              )
            }
  
            {!this.props.simple && (
              <div className="options">
                <input className="button" t="main" type="submit" value="Create" onclick="openPrompt('createGuest');" />
              </div>
            )}
  
          </div>
        )
      }
    }
  
    var yesterday = moment().subtract( 1, 'day' );
    var nextYear = moment().endOf("year")
    console.log(nextYear);
    var valid = function( current ){
      return current.isAfter( yesterday ) && current.isBefore( nextYear);
    };
  
    var test = new i.Input({ text: "Tests", name: "test" })
  
    return (
      <div id="createbooking">
        <h3>Create Booking</h3>
        <form method="POST" action="request?">
          <input type="hidden" name="action" value="createbooking" />
  
          <div className="bookingGuestContainer">
            <div>
              Your Guests:
              <GuestList id="bookingGuestLeft" simple="true" />
            </div>
            <div>
              Those you want to invite:
              <div id="bookingGuestRight" className="bookingGuest">
                <div className='list-group'></div>
              </div>
            </div>
          </div>
          
          <div>
            Date: <Datetime isValidDate={valid} dateFormat="MM-DD" timeFormat={false}/>
          </div>
          <div>
            Email Address:<input className="form-control" name="email" type="email" />
          </div>
          <input className="button" t="main" type="submit" value="Create Booking" />
        </form>
      </div>
    );
  }