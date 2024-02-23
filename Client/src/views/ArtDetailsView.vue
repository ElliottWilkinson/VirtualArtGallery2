<template>
  <art-details v-bind:artPiece="artPiece" />
</template>

<script>
import ArtDetails from '../components/ArtDetails.vue'
import GalleryService from '../services/GalleryService'

export default {

    components: {ArtDetails},

    data(){
        return {
            artPiece: {}, 
            isLoading: false 
        };
    }, 

    methods: {
        getArt(id){
            this.isLoading = true;
            GalleryService.getArtById(id)
            .then((response) => {
                this.artPiece = response.data;
                this.isLoading = false;
            })
            .catch((error) => {
                alert(error.message);
                this.isLoading = false;
            })
        }
    }, 

    created(){
        this.getArt(this.$route.params.artPieceId);
    }

}
</script>

<style>

</style>