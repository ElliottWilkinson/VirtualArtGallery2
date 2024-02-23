<template>
  <section id="art-card">
      <article class="art-card" v-for="artPiece in artPieces" :key="artPiece.artPieceId">
          <div class="art-image">
              <img :src="artPiece.imageName">
          </div>
          <router-link v-bind:to="{name: 'artDetails', params: {artPieceId: artPiece.artPieceId}}">
              <div class="art-caption action" data-id="1">{{artPiece.caption}}</div>
          </router-link>
          <div class="art-description">{{artPiece.description}}</div>
      </article>
  </section>
</template>

<script>
import GalleryService from '../services/GalleryService';
export default {

    props: ['artPieces'],

    computed: {
        isLoggedIn(){
            return this.$store.state.toke.length > 0;
        }
    }

}
</script>

<style>

    #art-details{
        display: flex;
        flex-wrap: wrap;
        gap: 20px;
        padding: 10px;
    }

    .art-details{
        display: grid;
        grid-template-areas:
        "image image"
        "title title"
        "description description";
        color: white;
        background-color: black;
        padding: 10px;

    }

    .art-image{
        grid-area: image;
    }

    .art-caption{
        grid-area: title;
    }

    .art-description {
        grid-area: description;
    }
    
</style>