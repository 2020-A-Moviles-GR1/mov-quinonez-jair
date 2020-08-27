/**
 * Pokemon.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    nombre: {
      type: 'string'
    },
    usuario: { //Many to one, nombre de la fk - mismo nombre que la relacion
      model: 'usuario',
      required: true
    },
    batallas: {
      collection: 'batalla',
      via: 'pokemon'
    }
  },

};

