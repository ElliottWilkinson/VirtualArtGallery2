<template>
  <div id="gallery-app">
      <header>
          put an image here !
      </header>

      <nav>
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token">
          Logout
        </router-link>
        <router-link v-bind:to="{ name: 'login' }" v-else>
          Login
        </router-link>
      </nav>
      
      <main>
        <router-view />
      </main>
      
      <footer> &copy; Jessy Kinsman and Elliott Wilkinson </footer>
  </div>
</template>


<style scoped>
 body {
   display: grid;
   grid-template-areas: 
   "header"
   "nav"
   "main"
   "footer";
   grid-template-rows: 200px 25px 1fr 25px;
   height: 100vh;
   font-family: Verdana, Geneva, Tahoma, sans-serif;
 }
</style>