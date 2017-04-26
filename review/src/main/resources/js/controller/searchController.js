/**
 * Created by hadoop on 4/25/17.
 */
angular.module('MyApp', [])
    .controller('searchController', ['$scope', '$http', function ($scope, $http) {
        $scope.list = {};
        $scope.results = [];

        $scope.search = function () {
         /*   $http.get({
                'http://localhost:8080/search_category',{searchParam:$scope.cate},
            }).then(function successCallback(response) {
                $scope.list = response.data;
            }, function errorCallback(response) {
                console.log(response.statusText);
            });*/
            $http
                .get('http://localhost:8080/search_category', {
                    params: {
                        searchParam: $scope.searchParam,
                    }
                })
                .success(function (data,status) {
                    debugger;
                    $scope.results = data
                });


        }
    }]);