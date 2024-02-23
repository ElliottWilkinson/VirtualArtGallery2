<template>
  <div class="home">
      
      <div id="heading-line">
          <h1>
              Recent Works
          </h1>
      </div>

    <art-cards v-bind:artPieces="artPieces"></art-cards>

  </div>
</template>

<script>
import artCards from '../components/ArtCards.vue';
import galleryService from '../services/GalleryService';

export default {

    components: {
        artCards
    }, 

    data() {
        return {
            isLoading: false, 
            artPieces: [],
        }
    }, 

    methods: {}, 

    created() {
        this.isLoading = true;

        galleryService.getArt()
        .then((response) => {
            this.artPieces = response.data;
            this.isLoading = false;
        })
        .catch((error) => {
            alert(error.message);
            this.isLoading = false;
        })
    }

}
</script>

<style>

</style>