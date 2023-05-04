import React, { useEffect, useState } from 'react';
import logo from '../logo.svg';
import Cookies from 'js-cookie';
import $ from 'jquery';
import './MainContainer.css';
import './PromptContainer.css';
import Nav from '../Nav/Nav';
import moment from 'moment';
import { LocalizationProvider } from '@mui/x-date-pickers';
import { AdapterMoment } from '@mui/x-date-pickers/AdapterMoment'
import { DatePicker } from '@mui/x-date-pickers';
import { TimePicker } from '@mui/x-date-pickers/TimePicker';

var key = 0;
var user = null;

var userid = Cookies.get('userid');
console.log({ userid: userid })

class Input extends React.Component {
  constructor(props) {
    super(props);
    this.o = props;
  }

  showTime() {

  }
  startBooking() {

  }

  render() {
    return (
      <div key={key++}>
        {this.o.text}<input placeholder={this.o.placeholder} id={this.o.id || null} key={key++} className={this.o.className || "form-control"} name={this.o.name} type={this.o.type || "text"} defaultValue={this.o.value} />
      </div>
    )
  }
}
class Hidden extends React.Component {
  constructor(props) {
    super(props)
    this.name = props.name || ""
    this.value = props.value || ""
  }
  render() {
    return (
      <input type="hidden" value={this.value} name={this.name} />
    )
  }
}
class Button extends React.Component {
  constructor(props) {
    super(props);
    this.o = props;
  }

  render() {
    return (
      <button key={key++} className={this.props.disabled == true ? "disabled button" : "button"} onClick={this.props.action} t={this.props.type || "main"}>{this.props.text || "Action"}</button>
    )
  }
}
class Icon extends React.Component {
  constructor(props) {
    super(props)
    this.name = this.props.name || "person"
    if (props.onClick)
      this.click = props.onClick.bind(this)
  }
  render() {
    return (
      <span key={key++} onClick={this.click} className="material-symbols-rounded">{this.name}</span>
    )
  }
}

$.putJSON = function (url, data, callback) {
  return $.ajax({
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      "Access-Control-Allow-Origin": "*"
    },
    'type': 'PUT',
    'url': url,
    'data': JSON.stringify(data),
    'dataType': 'json',
    'success': callback

  }).fail(function (jqXHR, textStatus) {
    DisplayMessage("Something Went Wrong...")
  });
};
$.postJSON = function (url, data, callback) {
  return $.ajax({
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      "Access-Control-Allow-Origin": "*"
    },
    'type': 'POST',
    'url': url,
    'data': JSON.stringify(data),
    'dataType': 'json',
    'success': callback

  }).fail(function (jqXHR, textStatus) {
    DisplayMessage("Something Went Wrong...")
  });
};
$.getJSON = function (url, callback) {
  return $.ajax({
    headers: {
      "Access-Control-Allow-Origin": "*"
    },
    'type': 'GET',
    'url': url,
    'success': callback

  }).fail(function (jqXHR, textStatus) {
    DisplayMessage("Something Went Wrong...")
  });
};
async function GetOneFrom(url, action) {
  return fetch(url, {
    headers: { "Access-Control-Allow-Origin": "*" }
  })
    .then((response) => {
      if (response.status === 200) {
        return response.json()
      }
    }).then((data) => {
      if (data) {
        action && action(data);
      }
    })
}
async function GetAllFrom(url, action) {
  return fetch(url, {
    headers: { "Access-Control-Allow-Origin": "*" }
  })
    .then((response) => {
      if (response.status === 200) {
        return response.json()
      }
    }).then((data) => {
      if (data) {
        var result = []
        for (var i in data) {
          result.push(data[i])
        }
        action && action(result);
      }
    })
}
function CheckList(list, msg) {
  return (list && list.length > 0) ? false : (<div className="empty">{msg}</div>)
}
function DisplayMessage(msg) {
  const m = $("#message")
  $(m).find("div").html(msg);
  $(m).animate({ opacity: 1 }, 300).delay(2500).animate({ opacity: 0 }, 500);
}
async function LoginAccount(user, callback) {
  return $.postJSON("http://localhost:8088/member/verify", user, callback)
}

function Front() {
  return (
    <div id="front">
      <h3>Welcome</h3>
      <p>We are Event Point Limited.</p>
    </div>
  );
}
class Venue extends React.Component {
  constructor(props) {
    super(props);
    this.id = props.id || null;
    this.o = props;
    if (props.toCreateBooking) {
      props.toBooking2 = () => {
        props.toCreateBooking(this.id)
      }
      this.toCreateBooking = props.toBooking2.bind(this);
    }
  }

  render() {
    if (this.id)
      return (
        <div key={key++} ishidden="false" className="block">
          <div className="img"><img className="rounded" src={"http://localhost:8088/img/venue/" + this.id + ".jpg"} /></div>

          <div className="info">

            <div className="name"><p>{this.o.name}</p></div>
            <div className="address"><Icon name="pin_drop" /><p>{this.o.address}</p></div>
            <div className="type"><p>{this.o.type}</p></div>
            <div className="staffname"><Icon name="support_agent" /><p>{this.o.staffname}</p></div>
            <div className="desc"><Icon name="description" /><p>{this.o.desc}</p></div>
            <div className="capacity"><Icon name="group" /><p>{this.o.capacity}</p></div>
            <div className="fee"><Icon name="attach_money" /><p>{this.o.fee}</p></div>

            {
              this.toCreateBooking && (<div className="book button" t="main" onClick={this.toCreateBooking}><span className="material-symbols-rounded">in_home_mode</span>
                <p>Book</p>
              </div>
              )}

            {
              this.toCreateBooking && (<div className="button time" t="main" onClick={this.toCreateBooking}><span className="material-symbols-rounded">event_available</span>
                <p>View Opening Hours</p>
              </div>)
            }


          </div>

        </div>
      );
    else
      return (<div></div>);
  }
}
function Venues({ toCreateBooking }) {
  const [venues, setVenue] = useState();
  useEffect(() => {
    GetAllFrom("http://localhost:8088/venue/ALL", setVenue)
  }, []);

  var toBk = function (id) {
    toCreateBooking(id)
  }
  return (venues && (
    <div id="venue" className="">
      <h3>Our Venues</h3>
      <div className='venue'>
        {CheckList(venues, "There are no avalible venues.") || venues.map((object) => {
          object.toCreateBooking = toBk
          return new Venue(object).render()
        })}
      </div>
    </div>
  )) || (<div>Loading</div>)
}
function Guests({ toCreateGuest, toEditGuest }) {
  const [guests, setGuest] = useState();

  useEffect(() => {
    GetAllFrom("http://localhost:8088/guest/ALL/" + userid, setGuest)
  }, []);

  var loadGuests = () => {
    setGuest(null);
    GetAllFrom("http://localhost:8088/guest/ALL/" + userid, (e) => {
      setGuest(e)
      DisplayMessage("List refreshed.")
    })
  }

  return (
    <div id="guest">
      <h3>Guest List</h3>
      <div className="options">
        <Button text="Refresh" action={loadGuests} type="sub" />
        <Button text="Create" action={toCreateGuest} type="main" />
      </div>
      {guests && <GuestList guests={guests} toCreateGuest={toCreateGuest} toEditGuest={toEditGuest} showEmpty={true} />}

    </div>
  )
}


class Guest extends React.Component {
  constructor(props) {
    super(props);
    if (props.edit) {
      var edit2 = () => {
        props.edit(this.props);
      }
      this.editGuest = edit2.bind(this)
    }
  }

  render() {
    if (!this.props.simple)
      return (
        <tr key={this.props.key}>
          <th scope="row">{this.props.key}</th>
          <td>{this.props.name}</td>
          <td>{this.props.email}</td>
          <td>
            <Button text="Edit" action={this.editGuest} />
          </td>
        </tr>)
    else
      return (
        <div key={key++} className="list-group-item list-group-item-action">
          <Hidden name="id" value={this.props.id} />
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
    this.guests = props.guests
    this.showEmpty = props.showEmpty || false
    if (props.toEditGuest) {
      this.edit = props.toEditGuest;
    }
    if (props.toCreateGuest) {
      this.create = props.toCreateGuest;
    }
  }
  render() {
    this.count = 1
    return (
      <div id={this.props.id} className={this.props.simple && "bookingGuest"}>
        {!this.props.simple ?
          (<table className="table">
            <thead>
              <tr>
                <th scope="col"></th>
                <th scope="col">Name</th>
                <th scope="col">Email</th>
                <th scope="col"></th>
              </tr>
            </thead>
            <tbody>
              {CheckList(this.guests, this.showEmpty ? "There are no avalible guest." : "") || this.guests.map((object) => {
                object.key = this.count++
                object.edit = this.edit
                return new Guest(object).render()
              })}
            </tbody>
          </table>
          ) : (
            <div className="list-group">
              {CheckList(this.guests, this.showEmpty ? "There are no avalible guest." : "") || this.guests.map((object) => {
                object.simple = true
                object.edit = this.edit
                return new Guest(object).render()
              })}
            </div>
          )
        }
      </div>
    )
  }
}
function CreateBooking({ venueid, toVenue }) {
  const [venue, setVenue] = useState()
  const [guests, setGuest] = useState();

  const [time, setTime] = useState({ isSet: false })
  const [date, setValue] = useState(moment());

  useEffect(() => {
    GetAllFrom("http://localhost:8088/guest/ALL/" + userid, setGuest).then(
      () => GetOneFrom("http://localhost:8088/venue/read/" + venueid, setVenue)
    )
  }, []);

  var nextday = moment().add(1, 'day');
  var nextYear = moment().endOf("year")

  var notValidDate = function (date) {
    var notInRange = date.isBefore(nextday) || date.isAfter(nextYear)
    if (venue && !notInRange) {
      venue.close.forEach(d => {
        var from = moment(d.from)
        var to = moment(d.to)
        notInRange = notInRange || !(date.isBefore(from) || date.isAfter(to))
      })
      var count = 0;
      venue.open.forEach(d => {
        if (d.from < 0) {
          notInRange = notInRange || date.day() == count
        }
        count++
      })
    }
    return notInRange
  }
  var onChange = function (date) {
    var time = { isSet: true };
    if (venue.open) {
      if (venue.open[date.day()]) {
        var t = venue.open[date.day()]
        time.start = moment().hour(t.from - 1).minutes(0)
        time.end = moment().hour(t.to - 1).minutes(0)
        time.value = date.hour(time.start.hour() + 1).minutes(0)
      }
    }
    setTime(time)
    setValue(date)
  }
  var onOpen = function (date) {
    setTime({ isSet: false })
  }
  var makeBooking = () => {

    var hour = parseInt($("#createbooking").find('input[name="session"]').val());

    if (time.value || hour) {
      var guests = []
      $("#bookingGuestRight").find('input[name="id"]').each(function () {
        guests.push($(this).val())
      })
      var obj = { guests: guests }
      $.postJSON("http://localhost:8088/booking/guestlist", obj, (res) => {
        obj = res;

        var remark = $("#createbooking").find('textarea[name="afewwords"]').val();
        var fee = venue.fee * hour;
        var object = {
          id: "default",
          memberid: userid,
          status: "default",
          createtime: "default",
          image: null,
          guestlistid: obj.id,
          remark: remark,
          checkintime: null,
          checkouttime: null,
          startdate: time.value.format('YYYY-MM-DD'),
          starthour: parseInt(time.value.format('HH')),
          totalhour: hour,
          fee: fee,
          template: $("#createbooking").find('select[name="template"]').val()
        }
        $.postJSON("http://localhost:8088/booking/create", object, (res) => {
          toVenue();
          DisplayMessage("You have successfully created a new booking.")
        })
      })
    }
    else {
      DisplayMessage("Please select a time for the event.")
    }
  }

  return ((venue && guests) && (
    <div id="createbooking">
      <h3>Book this venue</h3>
      <div className='venue booking'>
        {new Venue(venue || { key: 1 }).render()}
      </div>
      <input type="hidden" name="action" value="createbooking" />

      <div className="dateTimeSession">
        <LocalizationProvider dateAdapter={AdapterMoment}>
          <DatePicker
            label="Date of the event"
            value={date}
            shouldDisableDate={notValidDate}
            onChange={(newValue) => onChange(newValue)}
            onOpen={(newValue) => onOpen(newValue)}
          />
          <TimePicker
            ampm={false}
            label="Time (in hour)"
            value={time.value || moment().hour(12).minute(0)}
            minTime={time.start || moment().hour(12)}
            maxTime={time.end || moment().hour(12)}
            timeSteps={{ minutes: 60 }}
            disabled={!time.isSet}
            onChange={(newValue) => setValue(newValue)}
          />
        </LocalizationProvider>
        <div>
          <span>Length of the event (in hour)</span>
          <input name='session' type="number" />
        </div>

      </div>
      <div className="bookingGuestContainer">
        <div>
          <p>Your Guests:</p>
          <GuestList id="bookingGuestLeft" guests={guests} showEmpty={true} simple="true" />
        </div>
        <div>
          <p>Those you want to invite:</p>
          <GuestList id="bookingGuestRight" simple="true" />
        </div>
      </div>
      <div>
        Notification Style:
        <select name="template" className="form-select" aria-label="Disabled select example">
          <option defaultValue={true} value="dark">Dark Theme</option>
          <option value="bright">Bright Theme</option>
        </select>
      </div>
      <div>
        Add a few words:<textarea className="form-control" name="afewwords" type="text"></textarea>
      </div>
      <div className='option'>
        <Button type="main" text="Create Booking" action={makeBooking} />
        <Button type="sub" text="Check out other venues" action={toVenue} />
      </div>
    </div>
  )) || (<div>Loading</div>)
}
function Register({ toHome }) {

  var createAccount = () => {
    var fname = $("#register").find('input[name="fname"]').val()
    var lname = $("#register").find('input[name="lname"]').val()
    var email = $("#register").find('input[name="email"]').val()
    var phone = $("#register").find('input[name="phone"]').val()
    var pwd1 = $("#register").find('input[name="pwd1"]').val()
    var pwd2 = $("#register").find('input[name="pwd2"]').val()
    var passw = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$/;

    if (!fname || !lname || !email || !phone || !pwd1 || !pwd2) {
      DisplayMessage("Please fill in all the empty field(s).")
      return
    } else if (pwd1 !== pwd2) {
      DisplayMessage("Looks like your passwords don't match.")
      return
    } else if (!pwd1.match(passw)) {
      DisplayMessage("Please setup a better password.")
      return
    }

    var object = {
      fname: fname,
      lname: lname,
      email: email,
      phone: phone,
      pwd: pwd1
    }

    $.postJSON("http://localhost:8088/member/create", object, (res) => {
      toHome()
      DisplayMessage("You have registered your account.")
    })
  }
  return (
    <div id="register">
      <div>
        <h3>Become a member</h3>
      </div>

      <div>
        Name:
        <div className="input-group">
          <input name="fname" type="text" placeholder="First name" aria-label="First name"
            className="form-control" />
          <input name="lname" type="text" placeholder="Last name" aria-label="Last name"
            className="form-control" />
        </div>
      </div>

      <Input text="Email Address:" name="email" placeholder="e.g. hello@outlook.com" />
      <Input text="Phone:" name="phone" placeholder="8 digit phone number" />


      <Input text="Password:" name="pwd1" type="password" placeholder="your password" />
      <Input text="Confirm Password:" name="pwd2" type="password" placeholder="type it again to make sure you remember" />

      <div className='helper'><i>The password should be between 6 to 20 characters which contain at least one numeric digit, one uppercase and one lowercase letter. <strong>e.g. jhWJxJHG$7y64kGM</strong></i></div>


      <div className='option'>
        <Button text="Register Account" action={createAccount} type="main" />
        <Button type="sub" text="Maybe next time" action={toHome} />
      </div>
    </div>
  )
}
function LoginPrompt({ closePrompt, setUser }) {

  var login = () => {
    var email = $("#login").find('input[name="email"]').val()
    var pwd = $("#login").find('input[name="pwd"]').val()

    if (!email) {
      DisplayMessage("Please enter the email address of your account.")
      return;
    }
    else if (!pwd) {
      DisplayMessage("Please enter your password.")
      return;
    }
    LoginAccount({ pwd: pwd, email: email }, (e) => {
      if (e.id) {
        user = e;
        DisplayMessage("Welcome back.")
        setUser(e)
      }
    })
  }
  return (
    <Prompt
      closePrompt={closePrompt}
      confirm={login}
      cancel={closePrompt}
      id="login"
      name="Login"
      content={["",
        <div>
          <Input text="Email Address:" name="email" type="email" />
          <Input text="Password:" type="password" name="pwd" />
        </div>
        , ""]}
      cancelText="Return"
    />
  )
}

class Prompt extends React.Component {
  constructor(props) {
    super(props)

    this.confirmText = props.confirmText || "Confirm";
    this.cancelText = props.cancelText || "Cancel";
    this.name = props.name || "A Prompt";
    this.content = props.content || <div></div>;
    if (props.closePrompt)
      this.close = props.closePrompt.bind(this)
    if (props.confirm)
      this.confirm = props.confirm.bind(this)
    if (props.cancel)
      this.cancel = props.cancel.bind(this)
    this.cancelButtonType = props.cancelButtonType || "sub";
  }

  render() {
    return (
      <div key={key++} id={this.props.id}>
        <div className="header">
          <h3>{this.name}</h3>
          <Icon name="close" onClick={this.close} />
        </div>
        <div className="content">
          {this.content}
          <div className="options">
            <Button text={this.confirmText} action={this.confirm} type="main" />
            <Button text={this.cancelText} action={this.cancel} type={this.cancelButtonType} />
          </div>
        </div>
      </div>
    )
  }
}

class Booking extends React.Component {
  constructor(props) {
    super(props);

    this.status = props.status
    var statusCodes = { 0: "Awaits Approvol", 1: "Awaits Payment", 2: "Uploaded Receipt", 3: "Awaits Comment", 4: "Commented" }
    this.statusCode = statusCodes[props.status]
    
    if (props.edit) {
      var edit2 = () => {
        props.edit(this.props);
      }
      this.edit = edit2.bind(this)
    }
    if (props.toUpdateBookingRemark) {
      var edit3 = () => {
        props.toUpdateBookingRemark(this.props);
      }
      this.toUpdateBookingRemark = edit3.bind(this)
    }
  }

  render() {
    return (
      <tr key={this.props.key}>
        <th scope="row">{this.props.key}</th>
        <td>{this.props.createtime}</td>
        <td>{this.statusCode}</td>
        <td>
          {this.status == 0 && <Button disabled={true} text="Upload Receipt" />}
          {this.status == 1 && <Button action={this.edit} text="Upload Receipt" />}
          {this.status == 2 && <Button disabled={true} text="Upload Receipt" />}
          {this.status == 3 && <Button action={this.toUpdateBookingRemark} text="Leave a Comment" />}
          {this.status == 4 && <Button disabled={true} text="Leave a Comment" />}
        </td>

      </tr>)
  }
}
class BookingList extends React.Component {

  constructor(props) {
    super(props);
    this.count = 1;
    this.bookings = props.bookings

    this.showEmpty = props.showEmpty || false

    if (props.toUpdateBooking) {
      this.edit = props.toUpdateBooking;
    }
    if (props.toUpdateBookingRemark) {
      this.toUpdateBookingRemark = props.toUpdateBookingRemark;
    }
  }

  render() {
    var count = 1
    return (
      <div id={this.props.id}>
        <table className="table">
          <thead>
            <tr>
              <th scope="col"></th>
              <th scope="col">Created on</th>
              <th scope="col">Status</th>
              <th scope="col"></th>
            </tr>
          </thead>
          <tbody>
            {CheckList(this.bookings, this.showEmpty ? "There are no booking record." : "") || this.bookings.map((object) => {
              object.key = count++
              object.edit = this.edit
              object.toUpdateBookingRemark = this.toUpdateBookingRemark
              return new Booking(object).render()
            })}
          </tbody>
        </table>
      </div>
    )
  }
}
function EmailTest() {

  var sendEmail = () => {
    var to = $("#emailtest").find('input[name="to"]').val()
    var subject = $("#emailtest").find('input[name="subject"]').val()
    var head = $("#emailtest").find('input[name="head"]').val()
    var body = $("#emailtest").find('textarea[name="body"]').val()
    var date = $("#emailtest").find('input[name="date"]').val()
    var timeLoc = $("#emailtest").find('input[name="timeLoc"]').val()

    $.postJSON("http://localhost:8088/email", { to: to, subject: subject, head: head, body: body, date: date, timeLoc: timeLoc }, (e) => {
      console.log(e)
      DisplayMessage("You have sent an email.");
    })
  }
  return (
    <div id="emailtest">
      <Input text="TO" type="email" name="to" value="itisonho@outlook.com"/>
      <Input text="Subject" name="subject" value="A New Event"/>
      <Input text="Header" name="head" value="Hello Chan Tai Man!"/>
      Body:
      <textarea class="form-control" text="Body" name="body">Just a reminder for your upcoming event. You can see the details below</textarea>
      <Input text="Date" name="date" value="12 MAY"/>
      <Input text="Time and Address" name="timeLoc" value="3pm - 5pm</br>Happy Apple Farm" />
      <Button action={sendEmail} text="Send" />
    </div>
  )
}

function Bookings({ toUpdateBooking , toUpdateBookingRemark}) {
  const [bookings, setBooking] = useState();

  useEffect(() => {
    GetAllFrom("http://localhost:8088/booking/ALL/" + userid, setBooking)
  }, []);

  var loadGuests = () => {
    setBooking(null);
    GetAllFrom("http://localhost:8088/booking/ALL/" + userid, (e) => {
      setBooking(e)
      DisplayMessage("List refreshed.")
    })
  }

  return (
    <div id="booking">
      <h3>Booking List</h3>
      <div className="options">
        <Button text="Refresh" action={loadGuests} type="sub" />
      </div>
      {bookings && <BookingList toUpdateBookingRemark={toUpdateBookingRemark} toUpdateBooking={toUpdateBooking} bookings={bookings} showEmpty={true} />}

    </div>
  )
}

async function uploadFile(name) {
  let formData = new FormData();
  formData.append("file", $("#receiptImage").prop('files')[0])
  formData.append("name", name);;
  let response = await fetch('http://localhost:8088/upload', {
    method: "POST",
    body: formData
  });
  return response
}

function CreateGuestPrompt({ closePrompt }) {

  var createGuest = () => {

    var name = $("#createGuest").find('input[name="name"]').val()
    var email = $("#createGuest").find('input[name="email"]').val()

    if (!email) {
      DisplayMessage("Please enter the email of the Guest.")
      return;
    }
    else if (!name) {
      DisplayMessage("Please enter the name of the Guest.")
      return;
    }
    $.postJSON("http://localhost:8088/guest/create", { memberid: userid, name: name, email: email }, (res) => {
      closePrompt()
      DisplayMessage("You have created a guest.")
    })
  }
  return (
    <Prompt
      closePrompt={closePrompt}
      confirm={createGuest}
      cancel={closePrompt}
      id="createGuest"
      name="Create guest"
      content={["",
        <div>
          <Input text="Name:" name="name" />
          <Input text="Email Address:" name="email" type="email" />
        </div>
        , ""]}
      confirmText="Create" />
  )
}
function EditGuestPrompt({ closePrompt, guest }) {

  var editGuest = () => {

    var name = $("#editGuest").find('input[name="name"]').val()
    var email = $("#editGuest").find('input[name="email"]').val()

    if (!email) {
      DisplayMessage("Please enter the email of the Guest.")
      return;
    }
    else if (!name) {
      DisplayMessage("Please enter the name of the Guest.")
      return;
    }
    $.putJSON("http://localhost:8088/guest/update", { id: guest.id, memberid: userid, name: name, email: email }, (res) => {
      closePrompt()
      DisplayMessage("You have updated the record.")
    })
  }
  var deleteGuest = () => {

    $.getJSON("http://localhost:8088/guest/delete/" + guest.id, (res) => {
      closePrompt()
      DisplayMessage("You have deleted the guest record.")
    })
  }

  return (
    guest && <Prompt
      closePrompt={closePrompt}
      confirm={editGuest}
      cancel={deleteGuest}
      id="editGuest"
      name="Edit guest"
      content={["",
        <div>
          <Input text="Name:" name="name" value={guest.name} />
          <Input text="Email Address:" value={guest.email} name="email" type="email" />
        </div>
        , ""]}
      cancelText="Delete"
      cancelButtonType="no"
    />
  )
}
function UpdateBookingPrompt({ closePrompt, booking }) {
  var update = () => {

    var img = $("#updateBooking").find('input[name="img"]').val()

    if (!img) {
      DisplayMessage("You haven't upload the receipt yet.")
      return;
    }

    uploadFile(booking.id).then(res => {
      if (res.status == 200) {
        $.postJSON("http://localhost:8088/booking/receipt", { id: booking.id, status: parseInt(booking.status) + 1, image: booking.id + ".jpg" }, (e) => {
          console.log(e)
          closePrompt()
          DisplayMessage("You have updated the booking status.")
        })
      }
    })

  }
  return (
    <Prompt
      closePrompt={closePrompt}
      confirm={update}
      cancel={closePrompt}
      id="updateBooking"
      name={"Total: " + booking.fee}
      content={["",
        <div>
          <p>You can pay us via our FPS account: <strong>1233212345</strong></p>
          <p>Or our bank account: <strong>(Citi 123) 990 1234 5678</strong></p>
          <p>and upload <strong>a clear image</strong> of the receipt to let us know</p>
          <p></p>
          <input type="file" id="receiptImage" name="img" accept="image/*"></input>
        </div>
        , ""]}
      confirmText="Submit the recepit" />
  )
}
function BookingRemarkPrompt({ closePrompt, booking }) {
  var update = () => {

    var remark = $("#updateBookingComment").find('textarea[name="review"]').val()

    if (!remark) {
      DisplayMessage("You haven't written anything yet.")
      return;
    }

        $.postJSON("http://localhost:8088/booking/remark", { id: booking.id, status: parseInt(booking.status) + 1, remark: remark }, (e) => {
          console.log(e)
          closePrompt()
          DisplayMessage("You have updated the booking status.")
        })
      }
  return (
    <Prompt
      closePrompt={closePrompt}
      confirm={update}
      cancel={closePrompt}
      id="updateBookingComment"
      name={"Leave a comment"}
      content={["",
        <div>
          <p>What do you think about our service? Leave a comment below.</p>
          <br/>
          <textarea class="form-control" text="Body" name="review"></textarea>
      
        </div>
        , ""]}
      confirmText="Submit the review" />
  )
}

export default function MainContainer() {

  var getUser = () => {
    var user = {
      id: Cookies.get('userid'),
      name: Cookies.get('username'),
      email: Cookies.get('useremail'),
      phone: Cookies.get('userphone')
    };
    if (user.id) {
      return user
    }
    return null
  }
  const [user, setUser] = useState(
    getUser()
  )
  var loginSetUser = (user) => {
    if (user && user.id) {
      userid = user.id
      Cookies.set('userid', user.id, { SameSite: "None" })
      Cookies.set('username', user.fname + " " + user.lname, { SameSite: "None" })
      Cookies.set('useremail', user.email, { SameSite: "None" })
      Cookies.set('userphone', user.phone, { SameSite: "None" })
    }
    setUser(user);
    window.location.href = "http://localhost:3000";
  }
  var logout = () => {
    Cookies.remove('userid')
    Cookies.remove('username')
    Cookies.remove('useremail')
    Cookies.remove('userphone')
    window.location.href = "http://localhost:3000";
  }

  const [bookingVID, setBVID] = useState();
  const [guest, setGUEST] = useState(null);
  const [booking, setBOOKING] = useState(null);

  const [pch, setPC] = useState(false);

  const [lph, setLP] = useState(false);
  const [cgh, setCG] = useState(false);
  const [egh, setEG] = useState(false);
  const [ubh, setUB] = useState(false);
  const [brh, setBR] = useState(false);

  var toLoginPrompt = () => openPrompt(setLP)
  var toEditGuest = (guest) => {
    setGUEST(guest)
    openPrompt(setEG)
  }
  var toUpdateBooking = (booking) => {
    setBOOKING(booking)
    openPrompt(setUB)
  }
  var toUpdateBookingRemark = (booking) => {
    setBOOKING(booking)
    openPrompt(setBR)
  }
  var toCreateGuest = () => openPrompt(setCG)

  function closePrompt(func) {
    func(false)
    setPC(false);
  }
  function openPrompt(func) {
    var list = [setLP, setCG, setEG]
    list.forEach((e) => e(false))
    func(true)
    setPC(true);
  }

  const [cbh, setCB] = useState(false);
  const [vh, setV] = useState(false);
  const [fh, setF] = useState(true);
  const [gh, setG] = useState(false);
  const [rh, setR] = useState(false);
  const [bh, setB] = useState(false);
  const [eh, setE] = useState(false);

  function open(func) {
    var list = [setCB, setV, setF, setG, setG, setR, setB, setE]
    list.forEach((e) => e(false))
    func(true)
  }
  var toCreateBooking = function (id) {
    setBVID(id)
    open(setCB)
  }
  var toEmail = () => open(setE)
  var toVenue = () => open(setV)
  var toHome = () => open(setF)
  var toGuest = () => open(setG)
  var toRegister = () => open(setR)
  var toBooking = () => open(setB)

  return (
    <div id="main">

      {pch && <div className="prompt-container">
        {brh && <BookingRemarkPrompt booking={booking} closePrompt={() => { closePrompt(setBR) }} />}
        {ubh && <UpdateBookingPrompt booking={booking} closePrompt={() => { closePrompt(setUB) }} />}
        {lph && <LoginPrompt setUser={loginSetUser} closePrompt={() => { closePrompt(setLP) }} />}
        {cgh && <CreateGuestPrompt closePrompt={() => { closePrompt(setCG) }} />}
        {egh && guest && <EditGuestPrompt guest={guest} closePrompt={() => { closePrompt(setEG) }} />}
      </div>}
      <Nav toEmail={toEmail} toBooking={toBooking} logout={logout} user={user} toHome={toHome} toVenue={toVenue} toGuest={toGuest} toRegister={toRegister} toLogin={toLoginPrompt} />
      <div className="main-container">
        {eh && <EmailTest />}
        {bh && <Bookings toUpdateBookingRemark={toUpdateBookingRemark} toUpdateBooking={toUpdateBooking} />}
        {rh && <Register toHome={toHome} />}
        {gh && <Guests toCreateGuest={toCreateGuest} toEditGuest={toEditGuest} />}
        {cbh && bookingVID && <CreateBooking toVenue={toVenue} venueid={bookingVID} />}
        {fh && <Front />}
        {vh && <Venues toCreateBooking={toCreateBooking} />}
      </div>
      <div id="message">
        <div>
          Test
        </div>
      </div>
    </div>
  )
}