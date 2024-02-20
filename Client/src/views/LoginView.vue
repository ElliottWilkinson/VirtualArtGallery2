<template>
  <div id="login">
      <form v-on:submit.prevent="login">
          <h1>Sign In</h1>
          <div id="fields">
              <label for="username">Username</label>
              <input 
                type="text"
                id="username"
                placeholder="Username"
                v-model="user.username"
                required
                autofocus
              />
              <label for="password">Password</label>
              <input 
                type="password"
                id="password"
                placeholder="Password"
                v-model="user.password"
                required
              />
              <div><button type="submit">Sign In</button></div>
          </div>
          <hr/>
          New to the gallery? <router-link v-bind:to="{ name: 'register' }">Register here!</router-link>
      </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {

    data() {
        return {
            user: {
                username: "", 
                password: "", 
            }, 
        };
    }, 

    methods: {
        login() {
            authService
            .login(this.user)
            .then((response) => {
                if (response.status === 200) {
                    this.$store.commit("SET_AUTH_TOKEN", response.data.token);
                    this.$store.commit("SET_USER", response.data.user);
                    //this.$router.push("/"); <-- comment back in when home page is created
                }
            })
            .catch((error) => {
                const response = error.response;
                if(!response) {
                    alert(error);
                } else if (response.status === 401) {
                    alert("Invalid Username or Password");
                } else {
                    alert(response.message);
                }
            });
        }, 
    }, 

};
</script>

<style>

    #login {
        display: flex;
        text-align: center;
        padding: 10px;
    }

    #fields {
        display: grid;
        text-align: center;
        color: black;
        background-color: white;
    }
</style>