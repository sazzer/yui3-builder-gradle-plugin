/**
 * Example class for testing the Module Builder code
 * @class Model
 */
var Model = Y.Base.create("Model", Y.Model, [
], {
    /**
     * Construct a new model object
     * @method initializer
     */
    initializer: function() {
    }
}, {
    ATTRS: {
        /**
         * The ID of this model
         * @attribute id
         * @type String
         */
        id: {},
        /**
         * The name of this model
         * @attribute name
         * @type String
         */
        name: {}
    }
});

Y.namespace("Example").Model = Model;
