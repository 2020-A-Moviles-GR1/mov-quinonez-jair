/**
 * Pais.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    
    nombrePais: {
      type: 'string',
      minLength: 3,
      required: true
    },
    area: {
      type: 'string',
      required: true,
      minLength: 3
    },
    costa: {
      type: 'string',
      
      isIn: ['Si', 'No','INDEFINIDO'],
      defaultsTo: 'INDEFINIDO'
    },
    ciudad: {
      type:'string',
      required: true
    }
  },

};

