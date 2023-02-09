<template>
  <div class="forums-moderating">
      <h3>Forums you moderate:</h3>
      <p v-if="$store.state.forumsModerated.length==0">None yet! 
          <router-link v-bind:to="{name: 'createForum'}">Create Forum</router-link>
        </p>
      <ul>
          <li v-for="forum in $store.state.forumsModerated" v-bind:key="forum.forumId" >
              <router-link v-bind:to="{ name: 'forum', params:{id:forum.forumId}} " >
                  {{forum.forumName}}
              </router-link>
          </li>
      </ul>
  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>