import axios from 'axios';

export default {

    getArt() {
        return axios.get('/art')
    },

    getArtById(artId) {
        return axios.get(`/art/${artId}`)
    }, 

    getGallery() {
        return axios.get('/gallery')
    }, 

    getGalleryById(galleryId) {
        return axios.get(`/gallery/${galleryId}`)
    }, 

    getUser() {
        return axios.get('/users')
    }, 

    getUserById(userId){
        return axios.get(`/users/${userId}`)
    }

}