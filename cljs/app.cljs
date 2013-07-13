(ns app
  (:require
    [goog.net.XhrIo :as xhr]
    [cljs.core.async :as async :refer [chan close!]]
    [dommy.core  :as dom]
  )
  (:require-macros
    [cljs.core.async.macros :refer [go alt!]]
    [dommy.macros :refer [sel sel1]]
  )
)

(defn log [s]
  (.log js/console (str s)))

(log "Started")

(defn GET [url]
  (let [ch (chan 1)]
    (xhr/send url
              (fn [event]
                (let [res (-> event .-target .getResponseText)]
                  (go (>! ch res)
                      (close! ch)))))
    ch))

(go
  ; delay for 2 seconds to make it clear that page updates dynamically after initial load
  (log "Delaying ...")
  (<! (async/timeout 2000))
  (log "Sending GET ...")
  (dom/set-text! (sel1 :#log) (<! (GET "/sitemap.txt")))
  (log "Finished"))
