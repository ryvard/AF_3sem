'use strict';
angular.module('app', [])

        .controller('httpController', ['$scope', '$http', function ($scope, $http) {

//                var self = this;
                $scope.flightsFromDate;
                
                $http.get('http://airline-plaul.rhcloud.com/api/flightinfo/CPH/2017-01-20T00:00:00.000Z/1').then(function(response) {
                    
                    $scope.flightsFromDate = response.data.flights;
                    
                }, function(error) {
                    
                    
                });

            }]);

/*
        .factory('httpFactory', ['$http', function ($http) {

                var httpFactory = [];

                httpFactory.getFlights = function () {
                    var url = 'http://airline-plaul.rhcloud.com/api/flightinfo/CPH/2017-01-20/1';
                    console.log(url);
                    return $http.get(url);
                };

                return httpFactory;

            }]);
            */