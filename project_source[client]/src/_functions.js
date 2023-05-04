import $ from 'jquery';

export function hidePage(page){
    console.log(page);
    $(page).hide();
    $(page).css("position", "absolute");
}

export function showPage(page){
    console.log(page);
    $(page).css("position", "relative");
    $(page).show();
}