


<template>
  <div class="home">
    <div class="posts">
      <h2>Top 10 Posts Today</h2>
      <post v-for="post in $store.state.posts" v-bind:key="post.postId" v-bind:post="post" />
    </div>

    <div class="left-aside">
    <aside>
      <router-link
        v-bind:to="{ name: 'createForum' }"
        v-if="$store.state.token != ''">
        <button class="create-forum" id="buttons">Create Forum</button></router-link>
      <top-5-forums id="top5" />
      <div id="favorite-aside" v-if="$store.state.token != ''">
        <favorite-forums />
      </div>
      <div v-else class="anon-user">
        <h3>Create an account to follow your favorite communities and start taking part in conversations. </h3>
        <router-link :to="{ name: 'register' }"><button class="join-vortex" id="buttons">Join Vortex</button></router-link>
      </div>
    </aside>
    </div>

    <div class="right-aside">
    <aside>
        <big-button />
        <right-side-bar/>
        
    </aside>
    </div>

  </div>
</template>

<script>

import Top5Forums from "../components/Top5Forums.vue";
import Post from "../components/Post.vue";
import postsService from "../services/PostsService";
import interactionsService from "../services/InteractionsService";
import moderatorsService from "../services/ModeratorsService";
import favoriteService from "../services/FavoriteService";
import FavoriteForums from "../components/FavoriteForums.vue";
import RightSideBar from '../components/RightSideBar.vue';



export default {
  name: "home",
  components: {
    Top5Forums,
    Post,
    FavoriteForums,
    RightSideBar,
   
  },
  
  created() {
    postsService.getTop10().then((response) => {
      this.$store.commit("SET_POSTS", response.data);
    });
    if (this.$store.state.token != "") {
      interactionsService
        .getInteractions(this.$store.state.user.id)
        .then((response) => {
          this.$store.commit("SET_INTERACTIONS", response.data);
          console.log("interactions added");
        });
      favoriteService
        .getFavorites(this.$store.state.user.id)
        .then((response) => {
          this.$store.commit("SET_FAVORITES", response.data);
          console.log("favorites added");
        });
    }
    if (this.$store.state.token != "") {
      moderatorsService
        .getForumsModerated(this.$store.state.user.id)
        .then((response) => {
          this.$store.commit("SET_FORUMS_MODERATED", response.data);
          console.log("forums moderated added");
        });
    }
  },
};
</script>

<style >
.home{
display: grid;
grid-template-columns: 1fr 1fr 1fr;
grid-template-areas: "left-aside posts right-aside";
grid-gap: 20px;
margin-top: 100px;  
background-color: #fcfcfc;
}

.posts{
grid-area: posts;
}


.left-aside{
grid-area: left-aside;
position: fixed;
margin-top: 80px;
top: 0;
left: 10px;
bottom: 0;
height: 100vh;
border-right: 1px solid #cfcfcf;
padding: 2px;
background-color: #fcfcfc;
width: 18%;
box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.05);
display: flex;
flex-direction: column;

}

#top5{
  margin-top: 10px;
}
    
.right-aside{
grid-area: right-aside;
position: fixed;
margin-top: 80px;
top: 0;
right: 0;
bottom: 0;
height: 100vh;
border-left: 1px solid #cfcfcf;
padding-top: 10px;
padding-left: 3px;
background-color: #fcfcfc;
width: 18%;
box-shadow: -2px 2px 5px rgba(0, 0, 0, 0.05);
}

.join-vortex{
  border-radius: 13px;
  width: 105px;
  border: none;
  padding: 7px;
  font-weight: bold;
  cursor: pointer; 
  background-color: #23468A;
  color: #f3f3f3;
}

#buttons:hover {
  background-color: #1483d6;
}
#buttons{
 border-radius: 13px;
  width: 150px;
  border: none;
  padding: 7px;
  font-weight: bold;
  cursor: pointer; 
  background-color: #23468A;
  color: #f3f3f3;
}
.anon-user{
  
}

.create-forum{
  margin-right: 30px;
  
}
</style>








