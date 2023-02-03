<template>
  <div id="main-forum-feed">
    <!-- <div class="post" v-for="post in postsByForum" v-bind:key="post.postId">
      <router-link
        v-bind:to="{ name: 'postAndReplies', params: { id: post.postId } }"
      >
        <h3>{{ post.title }}</h3></router-link
      >

      <img v-if="post.img_url" :src="post.img_url" alt="Post Image" />
      <p>{{ post.postedAt }}</p>
      <p>{{ post.body }}</p>
    </div> -->
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