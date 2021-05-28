var age, height, weight, waist, female, male;
function cal(){
    age = document.getElementById("form").elements.item(0).value;
    weight = document.getElementById("form").elements.item(3).value;
    height = document.getElementById("form").elements.item(4).value;
    waist = document.getElementById("form").elements.item(5).value;
    female = document.getElementById("f").checked;
    male = document.getElementById("m").checked;
    console.log(age,height,weight,waist);
    calBodyMass();
    show();
}

function calBodyMass(){
    if(!female && !male){
        alert("Please select gender");}
    

    /*BMI*/
    //var age=22,height=170,weight=50,waist=50,female=false,male=true;
    var BMI=weight/Math.pow(height/100,2);
    
    document.getElementById("bmi").value = Math.round(BMI*100)/100;
    
    /*BodyFat*/
    var BodyFat;
    if(female){
        bodyFat = 1.2 * BMI + 0.23 * age - 5.4;
    }
    else
        bodyFat = 1.2 * BMI + 0.23 * age - 16.2;
    document.getElementById("bf").value = Math.round(bodyFat*100)/100;
    var fm = bodyFat*weight/100;
    
    document.getElementById("fm").value = Math.round(fm*100)/100;
    var lm = weight - fm;
    document.getElementById("lm").value = Math.round(lm*100)/100;
    var idealWeightMax, idealWeightMin;
    idealWeightMin = 20*height*height/10000;
    idealWeightMax = 25*height*height/10000;
    document.getElementById("bmih").value = idealWeightMax;
    document.getElementById("bmil").value = idealWeightMin;
    var whr = Math.round(waist/height*100)/100;
    document.getElementById("whr").value = whr;
    
    /*Daily Calories*/
    var RMR;
    if(female){
        RMR = 10*weight + 6.25*height - 5*age - 161;
    }
    else    RMR = 10*weight + 6.25*height - 5*age + 5;

    document.getElementById("rmr").value = RMR;
    
    var BMR;
    if(female){
        BMR = 655.1 + (4.35 /0.453592* weight) + (4.7 /2.54* height) - (4.7 * age); 
    }
    else BMR = 66 + (6.2/0.453592*weight) + (12.7/2.54*height) - (6.76*age);

    var activityLevel = document.getElementById("ActivityLevel").selectedIndex;
    var AL;
    if(activityLevel==0) AL = 1.2;
    else if (activityLevel==1) AL = 1.375;
    else if(activityLevel==2) AL = 1.55;
    else if(activityLevel==3) AL = 1.725;
    else if(activityLevel==4) AL = 1.9;
    var cn = Math.round(AL * BMR);
    document.getElementById("cn").value = cn;

    var calorieIntake;
    if(female){
        calorieIntake = Math.round(655.1 + 9.563*weight + 1.85*height - 4.676*age);
    }
    else    calorieIntake = Math.round(66.47 + 13.75*weight + 5.003*height - 6.755*age);
    document.getElementById("cg").value =   calorieIntake;

    document.getElementById("cb").value = cn - calorieIntake;
    document.getElementById("ci").value = calorieIntake;
    var pro = weight*0.9;
    document.getElementById("pro").value = pro;
    var fat = calorieIntake*0.3/9;
    document.getElementById("fat").value = Math.round(fat);
    var carb = 0.6 * calorieIntake/9;
    document.getElementById("carb").value = Math.round(carb);

    var mb;
    var hb;
    var lb;
    if(activityLevel==4){
        hb = 1;
        lb = 0.9;
    }
    else if (activityLevel==3) {
        hb = 0.9;
        lb = 0.8;
    }
    else if(activityLevel==2){
        hb = 0.8;
        lb = 0.7;
    }
    else if(activityLevel==1) {
        hb = 0.7;
        lb = 0.6;
    }
    else{
        hb = 0.6;
        lb = 0.5;
    }
    mb = 220-age;
    document.getElementById("bpmm").value = mb;
    document.getElementById("bpmh").value = Math.round(mb*hb);
    document.getElementById("bpml").value = Math.round(mb*lb);
}

function show(){
    var x = document.getElementsByClassName("result");
    x[0].style.display = 'block';
    x[1].style.display = 'block';
}
