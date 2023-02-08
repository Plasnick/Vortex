<template>
  <div class="favorite-forums">
    <h3>Your favorite Forums:</h3>
    <nav>
        <router-link v-for="forum in favoriteForums" v-bind:key="forum.id" v-bind:to="{ name: 'forum', params: { id: forum.id } }">{{
          forum.forumName
        }}</router-link>
    </nav>
  </div>
</template>

<script>
import forumsService from "../services/ForumsService";

export default {
  name: "favorite-forums-list",
  components: {},
  data() {
    return {
      favoriteForums: [],
    };
  },
  methods: {
    getFavoriteForumsByUserId(){
      if (this.$store.state.token != "") {
      forumsService
        .favoriteForumsByUserId(this.$store.state.user.id)
        .then((response) => {
          this.favoriteForums = response.data;
        });
    }
    }
  },
  created() {
    this.getFavoriteForumsByUserId()
  },
};
</script>

<style >
.favorite-forums {
  background-color: #f8f7f7;
  padding: 20px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);  
  height: 60vh;
}
nav a {  
  display: flex;
  align-items: center;
  padding: 10px;
  width: 100%;
  text-align: left;
  text-decoration: none;
  color: black;
}

nav {
  display: flex;
  flex-direction: column;
  background-color: #f8f7f7;
  width: 100%;
  padding: 10px;
}
nav a:hover {
  background-color: #e0e0e0;
  width: 100%;
}


h3 {
  margin: 0;
  font-size: 20px;
  color: #23468A;
}

</style>
