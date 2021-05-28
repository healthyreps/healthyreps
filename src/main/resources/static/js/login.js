/**
 *
 * @author Mustafa
 * @returns
 */
 
console.log("user login connected")
function userLogin()
{
        var email = document.getElementById("email").value;
        var pwd = document.getElementById("password").value;
        console.log(email)
        console.log(pwd)
        var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        if(email =='')
        {
                alert("Please enter email.");
        }
        else if(pwd=='')
        {
        alert("Enter the password");
        }
        else if(!filter.test(email))
        {
                alert("Enter valid email id.");
        }
        
        else
        {

                }
}
document.getElementById("loginButton").onclick = function(){userLogin()};