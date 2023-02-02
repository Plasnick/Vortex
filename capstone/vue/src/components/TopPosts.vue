<template>
  <div>
    <h2>Top Posts</h2>
    <ul v-if="posts.length">
      <li v-for="post in posts" v-bind:key="post.postId">
        {{ post.forumName }} - {{ post.title }}
        <button v-on:click="upVote(post.postId)">Upvote({{ post.upVotes }})</button>
        <button v-on:click="downVote(post.postId)">Downvote({{ post.downVotes }})</button>
        <p>{{ post.body }}</p>
        <template v-if="post.img_url && post.img_url.trim() !== ''">
          <img :src="post.img_url" alt="Post Image">
        </template>
        
      </li>
    </ul>
    <p v-else>No posts to display.</p>
  </div>
</template>

<script>
import postsService from '../services/PostsService';

export default {
  data() {
    return {
      posts: []
    }
  },
  methods: {
    upVote(id){
      const index = this.posts.findIndex((element) => (element.postId === id));
      this.posts[index].upVotes++;
      postsService.updatePost(id, this.posts[index]).then((response) => {
        if(response.status === 200){
          console.log('it worked')
        }
      });
    },
    downVote(id){
      const index = this.posts.findIndex((element) => (element.postId === id));
      this.posts[index].downVotes++;
      postsService.updatePost(id, this.posts[index]).then((response) => {
        if(response.status === 200){
          console.log('it worked')
        }
      });
    }
  },
  created() {
    postsService.getTop10().then(response => {
        this.posts = response.data.map(post => {
        return {
          ...post,
          forumName: 'Forum Name' 
        };
      });
    })
  }
}
</script>
