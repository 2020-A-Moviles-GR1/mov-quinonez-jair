/**
 * Ciudad.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    nombreCiudad: {
      type: 'string',
      minLength: 3,
      required: true
    },
    habitantes:{
      type:'string',
      required: true,
      minLength: 3
    },
    puerto:{
      type:'string',
      
      isIn: ['Si','No'],
      defaultsTo: 'INDEFINIDO'
    },
    alcalde:{
      type:'string',
      minLength: 6,
      required: true,
    }
    /* pais:{
      model:'pais',
      required: true
    } */
  },

};

