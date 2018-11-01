<template>
    <div>
        <h1>Login</h1>
            <label for="email">Login</label>
            <input type="email" required class="form-control" name="login" aria-describedby="emailHelp" placeholder="email@exemplo.com" v-model="usuario.login">
            <label for="senha">Senha</label>
            <input type="password" required class="form-control" placeholder="Informe sua senha" v-model="usuario.senha">
            <button v-on:click.prevent="" >Cadastrar</button>
    </div>
</template>

<script>

// eslint-disable-next-line
/* eslint-disable */
let APIURL = "http://localhost:8081"
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
           
        }
    }
}

</script>

