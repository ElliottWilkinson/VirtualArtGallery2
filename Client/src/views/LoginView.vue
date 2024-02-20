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
        
    }

}
</script>

<style>

</style>