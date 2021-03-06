 const selectDistrict = document.getElementById('selectDistricts');
console.log(selectDistrict.target);

const selectButton = document.getElementById('select_');
selectButton.addEventListener("change", function () {
    const select = document.getElementById("select_");
    const obj = { "id": select.value}
    //http://sc0489:8080/personalcook_war_exploded/
    //http://localhost:8084/sam_solutions_project_war/
    fetch( "http://sc0489:8080/personalcook_war_exploded/getDistricts",{
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
});
}));
});