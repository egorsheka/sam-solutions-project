






  const language = document.getElementById('language');
    if(language != null){
        language.innerHTML = '<button type="button" class="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="position: absolute; right: 0;  top: -15px; color:#ca6a45;  background-color: #343a40!important; border: 0;font-size: 20px;font-family: Bree Serif, serif;">Language </button><div class="dropdown-menu" style="right: 0; left: auto; top: 30px; background-color: #343a40!important;"><a class="dropdown-item app-nav-link-item" style="color: whitesmoke;" href="?lang=ru_RU">Russian</a> <div class="dropdown-divider" style="color: whitesmoke;"></div><a class="dropdown-item app-nav-link-item" style="color: whitesmoke;" href="?lang=de">Deutsch</a> <div class="dropdown-divider" style="color: whitesmoke;"></div> <a class="dropdown-item app-nav-link-item" style="color: whitesmoke;" href="?lang=en_US">English</a></div>';
    }
  const selectDistrict = document.getElementById('selectDistricts');
 const date = document.getElementById('orderedDate');
 if(date.value == ""){
     const date = new Date();
     const values = [ date.getDate(), date.getMonth() + 1 ];
     for( var id in values ) {
         values[ id ] = values[ id ].toString().replace( /^([0-9])$/, '0$1' );
     }
     const d =( values[ 0 ]+'-'+values[ 1 ]+'-'+date.getFullYear());
     console.log(d);
     date.value =  "2019-12-01";
 }
 function f() {
     const select = document.getElementById("select_");
     const obj = { "id": select.value}
     //http://sc0489:8080/personalcook_war_exploded/
     //http://localhost:8084/sam_solutions_project_war/
     fetch( "http://localhost:8084/sam_solutions_project_war/getDistrictsByTown",{ // try not to specify server here
         method: 'POST',
         headers: {
             'Content-Type': 'application/json;charset=utf-8'
         },
         body: JSON.stringify(obj)
     })
         .then(resp => resp.json()
         .then(json => {
         console.log(json);
     while (selectDistrict.length > 0) {
         selectDistrict.remove(0);
     }
     json.map(item => {
         const option_ = document.createElement("option");
     option_.setAttribute("value", item.id.toString());
     option_.innerHTML = item.name.toString();
     selectDistrict.appendChild(option_);

     if($("#labelId").text() !== "") {
         $("#selectDistricts").val($("#labelId").text());
     }

 });
 }));
 }

const selectButton = document.getElementById('select_');
selectButton.addEventListener("change", function () {
    f()
});
 f();
