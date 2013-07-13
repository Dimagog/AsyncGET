(ns server
  (:use ring.middleware.file
        ring.middleware.file-info
        ring.util.response))

(defn handler [request]
  (redirect "default.html"))
  
(def app
  (-> #'handler
    (wrap-file "html")
    (wrap-file-info)))
