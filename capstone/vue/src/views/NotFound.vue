<template>
  <div class="container">
    <h1>404 Not Found</h1>
    <p>Sorry, the page you are looking for could not be found.</p>
    <router-link v-bind:to="{ name: 'home'}">
        <h2>Back To Home</h2>
      </router-link>
    

  </div>
</template>

<script>
export default {
    name: "NotFound"
}
</script>

<style>
.container {
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 100vh;
    color: #23468A;
  }
</style>