var ToDoEntry = Y.Base.create("ToDoEntry", 
    Y.Model, 
    [], {
            /**
             * Synchronize the todo entry with the data store
             * @param action {String} The Sync action to perform
             * @param options {Object} Any options to the sync action
             * @param callback {Function} Callback to trigger after the action
             */
            sync: function(action, options, callback) {
                Y.log("Performing sync action: " + action);
                switch (action) {
                    case "create":
                        break;
                    case "read":
                        break;
                    case "update":
                        break;
                    case "delete":
                        break;
                    default:
                        callback("Unexpected action: " + action);
                        break;
                }
            }
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
                value: 5,
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
