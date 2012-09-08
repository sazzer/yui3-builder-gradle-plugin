var ToDoEntry = Y.Base.create("ToDoEntry", 
    Y.Model, 
    [], {
    }, {
        ATTRS: {
            /**
             * The ID of the entry
             * @attribute id
             * @type {String}
             */
            id: {},
            /**
             * When the entry was created
             * @attribute created
             * @type {Date}
             */
            created: {
                /**
                 * Validate that the value is a Date
                 * @param v {Any} The value to validate
                 */
                validator: function(v) {
                    return v instanceof Date;
                }
            },
            /**
             * When the entry is due
             * @attribute due
             * @type {Date}
             */
            due: {
                /**
                 * Validate that the value is a Date
                 * @param v {Any} The value to validate
                 */
                validator: function(v) {
                    return v instanceof Date;
                }
            },
            /**
             * The priority of the entry
             * @attribute priority
             * @type {Number}
             */
            priority: {
                /**
                 * Validate that the value is a number between 0 and 10 inclusive
                 * @param v {Any} The value to validate
                 */
                validator: function(v) {
                    return Y.Lang.isNumber(v) && v >= 0 && v <= 10;
                }
            },
            /**
             * The title of the entry
             * @attribute title
             * @type {String}
             */
            title: {
                validator: Y.Lang.isString
            },
            /**
             * The long description of the entry
             * @attribute description
             * @type {String}
             */
            description: {
                validator: Y.Lang.isString
            }
        }
    });

Y.namespace("ToDoList").ToDoEntry = ToDoEntry;
