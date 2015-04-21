(ns trptcolin.versioneer.core-test
  (:use clojure.test
        trptcolin.versioneer.core))

(deftest test-get-version
  (testing "with the system property set"
    (System/setProperty "bar.version" "1.2.3-SNAPSHOT")
    (is (= "1.2.3-SNAPSHOT" (get-version "foo" "bar"))))

  (testing "with the system property unset and the properties file present"
    (System/clearProperty "bar.version")
    ;; from the actual file in test/META-INF/foo/bar/
    (is (= "9.8.7" (get-version "foo" "bar"))))

  (testing "with both spots unset"
    (System/clearProperty "bar.version")
    (with-redefs [map-from-property-filepath (fn [f] nil)]
      (is (= "" (get-version "foo" "bar")))))

  (testing "with both spots unset and a default string passed"
    (System/clearProperty "bar.version")
    (with-redefs [map-from-property-filepath (fn [f] nil)]
      (is (= "N/A" (get-version "foo" "bar" "N/A"))))))

(deftest test-get-revision
  (testing "with the properties file present"
    ;; from the actual file in test/META-INF/foo/bar/
    (is (= "0000000000000000000000000000000000000000\n"
           (get-revision "foo" "bar"))))

  (testing "with the properties file not present"
    (with-redefs [map-from-property-filepath (fn [f] nil)]
      (is (= "" (get-version "foo" "bar")))))

  (testing "with the properties file not present and a default string passed"
    (System/clearProperty "bar.version")
    (with-redefs [map-from-property-filepath (fn [f] nil)]
      (is (= "N/A" (get-version "foo" "bar" "N/A"))))))
