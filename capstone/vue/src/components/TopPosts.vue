<template>
  <div>
    <h2>Top Posts</h2>
    <ul v-if="posts.length">
      <li v-for="(post, index) in posts" :key="index">
        {{ post.forumName }} - {{ post.title }}
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
import axios from 'axios';

export default {
  data() {
    return {
      posts: []
    }
  },
  mounted() {
    axios.get("/posts/top10").then(response => {
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
