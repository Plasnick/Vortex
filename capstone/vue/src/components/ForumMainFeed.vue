<template>
  <div id="main-forum-feed">
    <div v-for="post in postsByForum" v-bind:key="post.postId">
      <post  v-bind:post="post" />
    </div>
    
    <post v-for="post in postsByForum" v-bind:key="post.postId" v-bind:post="post" />
    
  </div> 

</template>

<script>
import PostsService from "../services/PostsService";
import Post from "../components/Post.vue"
import interactionsService from "../services/InteractionsService"

export default {
  name: "forum-main-feed",
  components: {
    Post
  },
  data() {
    return {
      postsByForum: [],
      errorMessage: false
    };
  },
 
  created() {
    PostsService.getForumPosts(this.$route.params.id).then((response) => {
      this.postsByForum = response.data;
      this.$store.commit("SET_POSTS", response.data)
    });
    interactionsService.getInteractions(this.$store.state.user.id).then((response) =>{
      this.$store.commit("SET_INTERACTIONS", response.data)
    })
  },
};
</script>

<style>
</style>
