<template>
  <div class="home">
    <div class="body">
      <h1>Home</h1>
      <p>You must be authenticated to see this</p>
      <search-forums />
      <top-posts/>
    </div>

    <aside>
      
      <top-5-forums />
    </aside>

  </div>
</template>

<script>
import Top5Forums from '../components/Top5Forums.vue'
import SearchForums from '../components/SearchForums.vue'
import TopPosts from '../components/TopPosts.vue';


export default {
  name: "home",
  components: {
    Top5Forums,
    SearchForums,
    TopPosts,
  }
};
</script>

<style scoped>
  .home{
    display: grid;
    grid-template-columns: 1fr 1fr 1fr 1fr;
    grid-template-areas: "sidebar body body body";
    grid-gap: 20px;
    margin-top: 20px;
    
  }
  .body{
    grid-area: body;
  }
  aside{
    grid-area: sidebar;
    border-top: 1px solid #000;
    border-right: 1px solid #000;
    border-bottom: 1px solid #000;
    border-radius: 10%;
    height: 100%;
    width: 200px; 
    margin-right: 20px;
    padding-left: 10px;
    position: fixed;
    height: 60vh; 

  }
</style>
