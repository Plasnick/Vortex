<template>
  <div class="favorite-checkbox">
    Favorite forum:
    <input
      type="checkbox"
      v-model="favoriteForum"
      v-on:change="onFavoritedChange()"
      v-bind:checked="favoriteForum"
    />
  </div>
</template>

<script>
import favoriteService from "../services/FavoriteService";
export default {
  name: "favorite-checkbox",
  props: ["forum"],
  data() {
    return {
      favoriteForum: false,
      favorite: {
        userId: this.$store.state.user.id,
        favoriteId: this.$route.params.id,
      },
    };
  },
  computed: {
    checkFavoriteForum() {
      let favoriteCheck = false;

      //do I need to

      for (let i = 0; i < this.$store.state.favorites.length; i++) {
        let currentObj = this.$store.state.favorites[i];
        if (
          currentObj.userId == this.$store.state.user.id &&
          currentObj.favoriteId == this.$route.params.id
        ) {
          favoriteCheck = true;
          console.log("made it in");
        }
      }
      return favoriteCheck;
    },
  },
  methods: {
    //   need to do add data like in the post vue for interaction

    //need try catches
    // need response status for the deleted post

    onFavoritedChange() {
      if (this.$store.state.token == "") {
        this.$router.push("/login");
      } else {
        if (this.favoriteForum) {
          this.$store.commit("ADD_FAVORITE", this.favorite);
          favoriteService.addFavorite(this.favorite);
        } else {
          this.$store.commit("DELETE_FAVORITE", this.favorite.favoriteId);
          favoriteService.deleteFavorite(this.favorite.favoriteId);
        }
      }
    },
  },
  created() {
    favoriteService.getFavorites(this.$route.params.id).then((response) => {
      this.favorites = response.data;
      this.$store.commit("SET_FAVORITES", response.data);
    });
  },
};
</script> 

<style>
</style>