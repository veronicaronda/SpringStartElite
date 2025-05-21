const filtriContainer = document.querySelector("#filtriContainer");
const closeBtn = document.querySelector(".closebtn");
const hamburger = document.querySelector(".main")
const sidebar = document.querySelector(".mySidebar");
const main = document.querySelector(".main");
/* Set the width of the sidebar to 250px and the left margin of the page content to 250px */
function openNav() {
  sidebar.classList.add("widthCostum");
  filtriContainer.classList.remove("invisible")
  closeBtn.classList.remove("invisible")
  hamburger.classList.add("invisible")


//  document.getElementById("main").style.marginLeft = "250px";
}

/* Set the width of the sidebar to 0 and the left margin of the page content to 0 */
function closeNav() {
  sidebar.classList.remove("widthCostum");
  filtriContainer.classList.add("invisible")
  closeBtn.classList.add("invisible")
  hamburger.classList.remove("invisible")
}