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
            console.log(this.usuario) 
           this.$http.post(APIURL+"/login",this.usuario)
           .then(function(resp){
               console.log(resp)
               console.log(resp.body)
           });
        }
    }
}

</script>

<style scoped>
#login{
    text-align: center;
}
label{
    text-align: center;
    margin-bottom: 50%
}
</style>
