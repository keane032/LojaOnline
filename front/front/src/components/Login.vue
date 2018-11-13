<template>
    <div id="login">
        <h1>Login</h1>
            <label for="email">Login</label>
            <input type="email" name="login" placeholder="email@exemplo.com" v-model="usuario.login">
            <br/>
            <label for="senha">Senha</label>
            <input type="password" placeholder="Informe sua senha" v-model="usuario.senha">
            <br/>
            <button v-on:click.prevent="logar()">Cadastrar</button>
    </div>
</template>

<script>
// eslint-disable-next-line
/* eslint-disable */
import {checkExpiretion} from "../auth/auth-header.js"
let APIURL = "http://localhost:8080"
export default {
    name:'Login',
    data () {
        return{
            usuario:{
                login:'',
                senha:''
            }
        }
    },
    methods:{
        logar(){ 
           this.$http.post(APIURL+"/login",this.usuario)
           .then(function(resp){
               let token = resp.headers.map.authorization[0]
               localStorage.setItem("token",token.split(" ")[1])
               checkExpiretion()
           });
        }
    }
}

</script>

<style scoped>
#login{
    text-align: center;
}
input{
    text-align: center;
    margin: 1%;
}
</style>
