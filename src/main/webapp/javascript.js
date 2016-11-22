'use strict';
angular.module('app', [])

        .controller('httpController', ['httpFactory', function (httpFactory) {

                var self = this;
                self.flightsFromDate;
                getFlightsFromDate();

                function getFlightsFromDate() {
                    httpFactory.getFlights()
                            .then(function (response) {
                                self.flightsFromDate = response.data;
                                console.log(self.flightsFromDate);
                            });
                };
            }])

        .factory('httpFactory', ['$http', function ($http) {

                var httpFactory = {};

                httpFactory.getFlights = function () {
                    var url = 'http://airline-plaul.rhcloud.com/api/flightinfo/CPH/2017-01-20T00:00:00.000Z/1';
                    console.log(url);
                    return $http.get(url);
                };

                return httpFactory;

            }]);