let togglestatus = false;
let togglenav = function(){
    let getsidebar = document.querySelector(".nav-sidebar");
    let getsidebarul = document.querySelector(".nav-sidebar ul");
    let getsidebartitle = document.querySelector(".nav-sidebar span");
    let getsidebarlink = document.querySelectorAll(".nav-sidebar a");
if(togglestatus === false){
    getsidebar.style.width = "150px";
    getsidebartitle.style.opacity = "0.5";
    let alength = getsidebarlink.length;
    for(let i = 0; i <alength; i++){
        getsidebarlink[i].style.opacity = "1";
        
    }
togglestatus = true;
}else{
    getsidebarul.style.visibility = "false";
    getsidebar.style.width = "50px";
    getsidebartitle.style.opacity = "0";
    let alength = getsidebarlink.length;
    for(let i = 0; i <alength; i++){
        getsidebarlink[i].style.opacity = "0";
    }
    togglestatus = false;
}
}