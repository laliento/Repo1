function formhash(form, password) {
    var p = document.createElement("input");
    form.appendChild(p);
    p.name = "app_password";
    p.type = "hidden";
    p.value = hex_sha512(password.value);
    password.value = "";
    form.submit();
}