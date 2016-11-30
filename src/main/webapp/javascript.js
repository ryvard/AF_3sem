'use strict';
angular.module('resultModule', [])

        .controller('searchBarCntr', ['$scope', function ($scope) {
           $scope.showSearchBar = false;    
           console.log($scope.showSearchBar);
        }])

        .controller('searchCntr', ['$scope', '$http', function ($scope, $http) {
                $scope.airlineInfo;
                $scope.departAirport;
                $scope.arrivalAirport;
                $scope.date;
                $scope.tickets;
                $scope.iataCodes;

                $scope.flightInfo;


                $http.get('https://iatacodes.org/api/v6/airports?api_key=8a2623ff-1ca6-4250-aa10-838fb259775a').then(function (response) {
                    $scope.iataCodes = response.data;
                }, function (error) {

                });

                $scope.getFlights = function () {
                    $http.get('http://localhost:8080/AF_3sem/api/flights/'
                            + $scope.departAirport + '/' + $scope.date + '/' + $scope.tickets).then(function (response) {
                        $scope.airlineInfo = response.data;
//                        dataFactory.set($scope.airlineInfo);
                        console.log(response.data);
                        console.log($scope.airlineInfo.airline);
                    }, function (error) {
                    });

                };



            }])

//        .factory('dataFactory', ['$scope', function($scope) {
//              var searchData = {};
//              function set(data){
//                  searchData = data;
//              }
//              function get(){
//                return searchData;  
//              }
//              return{
//                  set:set,
//                  get:get
//              };
//              
                         
//            
//        }])

       

        .filter('durationFilter', function () {
            return function (time) {
                return (time / 60 | 0) + "h " + time % 60 + "m";
            };
        })
        .filter('arivalFilter', function () {
            return function (time, duration) {
                var timeSplit = time.split(":");
                return (parseInt(timeSplit[0]) + (duration / 60 | 0)) + ":" + (parseInt(timeSplit[1]) + duration % 60);
            };
        });
