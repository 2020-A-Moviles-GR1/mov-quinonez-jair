/**
 * Usuario.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    cadula: { //nombre atributo
      type: 'string',
      required: true,
      columnName: 'epn_cedula',
      unique: true,
      minLength: 10,
      maxLength: 25

    },
    nombre: {
      type:'string',
      minLength: 3,
      required: true, //por defeco es false 
    },
    correo:{
      type:'string',
      isEmail: true
    },
    estadoCivil:{
      type: 'string',
      isIn: ['Soltero','Casado', 'Divorciado','Viudo','Union Libre'],
      defaultsTo: 'Soltero' //Valor por defecto
    },
    password:{
      type:'string',
      regex: /^[a-zA-Z]\w{3,14}$/
    },
    pokemons:{ //one to many (Plural)
      collection: 'pokemon', //Referenciai al modelo
      via:'usuario' //Nombre foreign Key en 'Pokemon'
    }
  },
};