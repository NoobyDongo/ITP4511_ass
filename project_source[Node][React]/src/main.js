function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2)
        return parts.pop().split(';').shift();
}

$(document).ready(function () {

    if ($('#message').html()) {
        $('#message').collapse('show');
    }
    var currentpage = $("#cpage").val() || "front";
    var currentprompt = $("#cprompt").val() || null;

    console.log(currentpage);
    console.log(currentprompt);

    if (currentpage === '""' || currentpage === 'null') {
        currentpage = "front";
    }
    if (currentprompt === '""' || currentprompt === 'null') {
        currentprompt = null;
    }

    $(".prompt-container").hide();
    $(".prompt-container>div").hide();
    $(".main-container>div").hide();
    $(".main-container>div").css("position", "absolute");
    $("#" + currentpage).css("position", "relative");
    $("#" + currentpage).show();
    $(".main-container").css("opacity", 1);

    if (currentprompt !== null) {
        openPrompt(currentprompt);
    }
    $('body').removeAttr("hidden").delay(400).animate({opacity: '1'}, 250);

    var hidden = false;
    $("#message").on("click", function (event) {
        if (!hidden) {
            hidden = true;
            $('#message').collapse('hide');
        }
    });
});

function hidePage(id){
    console.log(id);
    $("#" + id).hide();
    $("#" + id).css("position", "absolute");
}

function showPage(id){
    console.log(id);
    $("#" + id).css("position", "relative");
    $("#" + id).show();
}

function closePrompt(id) {
    console.log(id);
    $(".prompt-container").hide();
    $("#" + id).hide();
}

function toBooking(id){
    showPage("createbooking");
    hidePage("venue");
}

function openPrompt(id) {
    console.log(id);
    $(".prompt-container").css("opacity", 1);
    $(".prompt-container").show();
    $("#" + id).show();

}

function editGuest(id, name, email) {
    console.log(id);
    console.log(name);
    console.log(email);
    var form = $("#editguest>form");
    $(form).find("input[name='name']").val(name);
    $(form).find("input[name='id']").val(id);
    $(form).find("input[name='email']").val(email);
    openPrompt("editguest");
}

function submitForm(action, id) {
    var form = $("#" + id + ">form");
    $(form).find("input[name='action']").val(action);
    $(form).submit();
}