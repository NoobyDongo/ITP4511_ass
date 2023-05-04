import React from 'react';

var key = 0;
export class Input extends React.Component {
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
                {this.o.text}<input id={this.o.id || null} key={key++} className={this.o.className || "form-control"} name={this.o.name} type={this.o.type || "text"} value={this.o.value} />
            </div>
        )
    }
}
export class Hidden extends Input {
    constructor(props) {
        super({
            name: props.name,
            value: props.value,
            type: "hidden"
        })
    }
}
export class Button extends React.Component {
    constructor(props) {
        super(props);
        this.o = props;
    }

    render() {
        return (
            <button className="button" onclick={this.props.action} t={this.props.type || "main"}>{this.props.text || "Action"}</button>
        )
    }
}